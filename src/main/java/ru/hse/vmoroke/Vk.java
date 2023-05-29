package ru.hse.vmoroke;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.wall.WallpostFull;
import javafx.application.HostServices;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * Класс для работы с VK .
 */
public class Vk implements HttpRequestHandler {
    private HostServices hostServices;
    private MainController mainController;
    private AtomicReference<String> gotCode = new AtomicReference<>();
    private HttpServer server;
    private String redirectURI;
    private VkApiClient vk;
    private String code;
    private UserActor actor;

    /**
     * Конструктор класса Vk.
     *
     * @param hostServices    Сервисы хоста для открытия URL.
     * @param mainController  Контроллер главного окна.
     */

    Vk(HostServices hostServices, MainController mainController) {
        this.hostServices = hostServices;
        this.mainController = mainController;
    }
    /**
     * Запускает процесс аутентификации VK.
     *
     * @throws Exception В случае возникновения ошибки.
     */

    public void authenticateStart() throws Exception {
        //https://oauth.vk.com/authorize?client_id=1&display=page&redirect_uri=http://example.com/callback&scope=friends&response_type=code&v=5.131
        server = ServerBootstrap.bootstrap().registerHandler("/", this).create();
        server.start();

        redirectURI = String.format("http://localhost:%d", server.getLocalPort());

        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        URI uri = new URIBuilder("https://oauth.vk.com/authorize")
                .addParameter("client_id", "51630864")
                .addParameter("display", "page")
                .addParameter("redirect_uri", redirectURI)
                .addParameter("scope", "friends,wall")
                .addParameter("response_type", "code")
                .addParameter("v", vk.getVersion())
                .build();

        System.out.println(uri.toString());
        hostServices.showDocument(uri.toString());
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        String uri = httpRequest.getRequestLine().getUri();
        List<NameValuePair> parameters = URLEncodedUtils.parse(uri.replaceFirst("^/\\?", ""), UTF_8);
        for (NameValuePair kv: parameters) {
            if (kv.getName().equals("code")) {
                gotCode.set(kv.getValue());
                break;
            }
            System.out.printf("'%s' = '%s'\n", kv.getName(), kv.getValue());
        }
        //StringEntity entity = new StringEntity("<html><head><title>Вход выполнен</title><script lang=\"javascript\">window.close();</script></head><body><h1>Вход выполнен</h1></body></html>", UTF_8);
        //entity.setContentType("text/html");
        //httpResponse.setEntity(entity);

        server.stop();
        server = null;

        code = gotCode.get();

        // enable Main buttons, menus, etc.
        mainController.vkAuthButton.setDisable(true);
        mainController.vkWallButton.setDisable(false);
    }
    /**
     * Обрабатывает комментарии со стены VK.
     *
     * @throws Exception В случае возникновения ошибки.
     */
    public void processWallComments() throws Exception {
        //ClientResponse r = transportClient.get(uri.toString(), new Header[1]);
        //System.out.println(r.getContent());
        if (actor == null) {
            UserAuthResponse authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(51630864, "Gw4ctWhvYXMfwbGBmWrQ", redirectURI, code)
                    .execute();
            actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        }

        /*GetResponse getResponseFriends = vk.friends().get(actor)
                .order(GetOrder.HINTS)
                .execute();*/
        com.vk.api.sdk.objects.wall.responses.GetResponse getResponseWall = vk.wall().get(actor).execute();
        for (WallpostFull wallpostFull:getResponseWall.getItems()){
            System.out.println(wallpostFull.getText()+" o");
        }
        System.out.println(getResponseWall.getItems().get(1).getText());
    }
}
