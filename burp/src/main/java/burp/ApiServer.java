package burp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import static spark.Spark.*;

public class ApiServer {


    public ApiServer(String ip, int port, IBurpExtenderCallbacks callbacks) {

        setPort(port);
        setIpAddress(ip);

        Gson gson = new Gson();

        get("/scanissues", (request, response) -> {
            IScanIssue[] rawIssues =  callbacks.getScanIssues("");
            List<BScanIssue> issues = new ArrayList<>();
            for (IScanIssue issue : rawIssues) {
                issues.add(BScanIssueFactory.create(issue, callbacks));
            }
            return gson.toJson(issues);
        });
    }

    public void stopServer() {
        stop();
    }

}
