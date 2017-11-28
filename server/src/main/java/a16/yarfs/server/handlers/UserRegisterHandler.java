/**
 * Created by nuno at 28/11/17
 */
package a16.yarfs.server.handlers;

import a16.yarfs.server.domain.Manager;
import a16.yarfs.server.exception.http.BadRequestException;
import a16.yarfs.server.exception.http.HttpException;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class UserRegisterHandler
 * nuno is an IDIOT because it hasn't made documentation for this class.
 */
public final class UserRegisterHandler extends AbstractHttpHandler {

    public UserRegisterHandler(String ...  methods){
        super(methods);
    }

    @Override
    public void handle(HttpExchange httpExchange) {
//            log.debug("Handling register request");
        //if(!httpExchange.getRequestMethod().equals("POST")){
        //   throw new UnsupportedOperationException();
        //}
        JSONObject body = null;
        try {
            super.handle(httpExchange);
            body = super.getBodyAsJson(httpExchange);
            Manager.getInstance().registerUser(body.getString("username"), body.getString("password"));
            sendResponse(200,"This is test "+body, httpExchange);
        } catch (IOException e) {
            e.printStackTrace(); // FIXME: Do something useful here
            //System.out.println("Body is "+body);
        } catch (HttpException e){
            sendResponse(e.getCode(),e.getMessage(),httpExchange);
        } catch (JSONException e) {
            //e.printStackTrace();
            BadRequestException bre = new BadRequestException();
            sendResponse(bre.getCode(), bre.getMessage(), httpExchange);
        }
    }
}