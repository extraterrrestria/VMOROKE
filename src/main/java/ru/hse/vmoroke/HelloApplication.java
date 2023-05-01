package ru.hse.vmoroke;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.apache.http.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.client.utils.URLEncodedUtils;


import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.exit;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HelloApplication extends Application {
    public static Stage mainStage;

    private void testVKCode() throws Exception {
        AtomicReference<String> gotCode = new AtomicReference<>();
        //https://oauth.vk.com/authorize?client_id=1&display=page&redirect_uri=http://example.com/callback&scope=friends&response_type=code&v=5.131
        HttpServer server = ServerBootstrap.bootstrap()
                .registerHandler("/", (httpRequest, httpResponse, httpContext) -> {
                    String uri = httpRequest.getRequestLine().getUri();
                    List<NameValuePair> parameters = URLEncodedUtils.parse(uri.replaceFirst("^/\\?", ""), UTF_8);
                    for (NameValuePair kv: parameters) {
                        if (kv.getName().equals("code")) {
                            gotCode.set(kv.getValue());
                            break;
                        }
                        System.out.printf("'%s' = '%s'\n", kv.getName(), kv.getValue());
                    }
                    httpResponse.setEntity(new StringEntity("AAAAAAAAAAAAA", UTF_8));
                })
                .create();
        server.start();

        String redirectURI = String.format("http://localhost:%d", server.getLocalPort());

        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        URI uri = new URIBuilder("https://oauth.vk.com/authorize")
                .addParameter("client_id", "51630864")
                .addParameter("display", "page")
                .addParameter("redirect_uri", redirectURI)
                .addParameter("scope", "friends")
                .addParameter("response_type", "code")
                .addParameter("v", vk.getVersion())
                .build();

        System.out.println(uri.toString());
        getHostServices().showDocument(uri.toString());

        while (gotCode.get() == null) {
            Thread.sleep(1000);
        }

        server.stop();

        String code = gotCode.get();

        //ClientResponse r = transportClient.get(uri.toString(), new Header[1]);
        //System.out.println(r.getContent());
        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(51630864, "Gw4ctWhvYXMfwbGBmWrQ", redirectURI, code)
                .execute();

        //UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        exit(1);
    }

    @Override
    public void start(Stage stage) throws Exception {
        testVKCode();
        exit(1);
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    //private static void handleRequest(HttpExchange exchange) throws IOException {
    //}
}
