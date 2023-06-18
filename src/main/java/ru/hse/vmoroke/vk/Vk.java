package ru.hse.vmoroke.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.responses.GetByIdObjectLegacyResponse;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import javafx.application.HostServices;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import ru.hse.vmoroke.MainController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Класс для работы с VK .
 */
public class Vk implements HttpRequestHandler {
    private final HostServices hostServices;
    private final MainController mainController;
    private final AtomicReference<String> gotCode = new AtomicReference<>();
    private HttpServer server;
    private String redirectURI;
    private VkApiClient vk;
    private String code;
    private UserActor actor;

    private List<GetByIdObjectLegacyResponse> usersGroups;

    /**
     * Конструктор класса Vk.
     *
     * @param hostServices   Сервисы хоста для открытия URL.
     * @param mainController Контроллер главного окна.
     */

    public Vk(HostServices hostServices, MainController mainController) {
        this.hostServices = hostServices;
        this.mainController = mainController;
    }

    /**
     * Запускает процесс аутентификации VK.
     *
     * @throws Exception В случае возникновения ошибки.
     */

    public void authenticateStart() throws Exception {
        server = ServerBootstrap.bootstrap().registerHandler("/", this).create();
        server.start();

        redirectURI = String.format("http://localhost:%d", server.getLocalPort());

        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        URI uri = new URIBuilder("https://oauth.vk.com/authorize")
                .addParameter("client_id", "51630864")
                .addParameter("display", "page")
                .addParameter("redirect_uri", redirectURI)
                .addParameter("scope", "groups,friends")
                .addParameter("response_type", "code")
                .addParameter("v", vk.getVersion())
                .build();
        hostServices.showDocument(uri.toString());
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        String uri = httpRequest.getRequestLine().getUri();
        List<NameValuePair> parameters = URLEncodedUtils.parse(uri.replaceFirst("^/\\?", ""), UTF_8);
        for (NameValuePair kv : parameters) {
            if (kv.getName().equals("code")) {
                gotCode.set(kv.getValue());
                break;
            }
        }

        server.stop();
        server = null;

        code = gotCode.get();
        mainController.vkAuthButton.setDisable(true);
        mainController.vkWallButton.setDisable(false);
    }

    /**
     * Обрабатывает комментарии со стены VK.
     *
     * @throws Exception В случае возникновения ошибки.
     */
    public String[] processUsersWallComments() throws ClientException, ApiException {
        if (actor == null) {
            UserAuthResponse authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(51630864, "Gw4ctWhvYXMfwbGBmWrQ", redirectURI, code)
                    .execute();
            actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        }
        com.vk.api.sdk.objects.wall.responses.GetResponse getResponseWall = vk.wall().get(actor).execute();
        List<String> comments = new ArrayList<>();
        for (WallpostFull wallpostFull : getResponseWall.getItems()) {

            if ((wallpostFull.getLikes().canPublish())&&(wallpostFull.getComments().getCount() != 0)) {
                GetCommentsResponse commentsInPost = vk.wall().getComments(actor).postId(wallpostFull.getId()).execute();
                for (WallComment wallComment : commentsInPost.getItems()) {
                    if ((wallComment.getFromId().equals(actor.getId())) & (!wallComment.getText().equals(""))) {
                        comments.add(wallComment.getText());
                    }
                }
            }
            if ((wallpostFull.getFromId().equals(actor.getId())) & (!wallpostFull.getText().equals(""))) {
                comments.add(wallpostFull.getText());

            }
        }
        return comments.toArray(new String[comments.size()]);
    }

    public String[] getUsersGroups() throws Exception {
        if (actor == null) {
            UserAuthResponse authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(51630864, "Gw4ctWhvYXMfwbGBmWrQ", redirectURI, code)
                    .execute();
            actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        }
        GetResponse response = vk.groups().get(actor).execute();
        List<String> groups = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : response.getItems()) {
            stringBuilder.append(i).append(",");
        }

        usersGroups = vk.groups().getByIdObjectLegacy(actor).groupIds(stringBuilder.toString()).execute();
        for (GetByIdObjectLegacyResponse i : usersGroups) {
            groups.add(i.getId().toString());

        }

        return groups.toArray(new String[groups.size()]);
    }

    public String[] getUsersCommentsInGroupsArray(String... usersGroupsId) throws ClientException, ApiException, InterruptedException {
        List<String> comments = new ArrayList<>();
        for (String groupId : usersGroupsId) {
            for (GetByIdObjectLegacyResponse usersGroup : this.usersGroups) {
                if (groupId.equals(usersGroup.getId().toString())) {
                    com.vk.api.sdk.objects.wall.responses.GetResponse response = vk.wall().get(actor).unsafeParam("owner_id", -usersGroup.getId()).execute();
                    for (WallpostFull wallpostFull : response.getItems()) {
                        if (wallpostFull.getLikes().canPublish()) {
                            Thread.sleep(333);
                            GetCommentsResponse commentsInPost = vk.wall().getComments(actor).ownerId(-usersGroup.getId()).postId(wallpostFull.getId()).execute();
                            for (WallComment wallComment : commentsInPost.getItems()) {
                                if ((wallComment.getFromId().equals(actor.getId())) & (!wallComment.getText().equals(""))) {
                                    comments.add(wallComment.getText());
                                }
                            }
                        }
                        if ((wallpostFull.getFromId().equals(actor.getId())) & (!wallpostFull.getText().equals(""))) {
                            comments.add(wallpostFull.getText());
                        }
                    }
                }
            }
        }

        return comments.toArray(new String[comments.size()]);
    }

    public void stopServer() {
        if (server != null) {
            server.stop();
        }
    }
}

