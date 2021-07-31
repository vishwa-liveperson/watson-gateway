package watson.gateway.util;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCallback;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.MessageInput;
import com.ibm.watson.assistant.v1.model.MessageOptions;
import com.ibm.watson.assistant.v1.model.MessageResponse;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import watson.gateway.domain.AssistantBuilder;
import watson.gateway.domain.Credentials;
import watson.gateway.dto.PredictIntent;
import watson.gateway.dto.PredictResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssistantExample {


    public static void main(String[] args) throws Exception {
        Credentials credentials = new Credentials();
        AssistantBuilder assistantBuilder = new AssistantBuilder(credentials);
        List<String> textList = new ArrayList<>();
        textList.add("Can you please find me something to watch?");
        textList.add("Can I speak to someone?");
        List<PredictResponse> predictResponseList = predict(assistantBuilder, textList, "43ee4d5f-d2cf-401e-8a01-65bf71eb7655");
        System.out.println(predictResponseList);
    }

    private static List<PredictResponse> predict(AssistantBuilder assistantBuilder, List<String> textList, String workspaceId) {
        List<PredictResponse> predictResponseList = new ArrayList<>();
        for(String text : textList){
            MessageInput input = new MessageInput();
            input.setText(text);
            MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).build();

            MessageResponse response = assistantBuilder.getAssistant().message(options).execute().getResult();
            List<PredictIntent> predictIntents = response.getIntents().
                    stream().
                    map(res -> new PredictIntent(res.intent(), res.confidence())).
                    collect(Collectors.toList());

            predictResponseList.add(new PredictResponse(text, predictIntents));
        }

        return predictResponseList;
    }

    public static void main2(String[] args) throws Exception {
        Credentials credentials = new Credentials();
        AssistantBuilder assistantBuilder = new AssistantBuilder(credentials);
//        predict(assistantBuilder);
        // async
//        ayncProcess(service, options);
        return;
    }

    private static void ayncProcess(Assistant service, MessageOptions options) throws InterruptedException {
        service
                .message(options)
                .enqueue(
                        new ServiceCallback<MessageResponse>() {
                            @Override
                            public void onResponse(Response<MessageResponse> response) {
                                System.out.println(response.getResult());
                            }

                            @Override
                            public void onFailure(Exception e) {
                            }
                        });

        // RxJava
        Single<Response<MessageResponse>> observableRequest =
                service.message(options).reactiveRequest();
        observableRequest
                .subscribeOn(Schedulers.single())
                .subscribe(
                        new Consumer<Response<MessageResponse>>() {
                            @Override
                            public void accept(Response<MessageResponse> response) throws Exception {
                                System.out.println(response.getResult());
                            }
                        });

        Thread.sleep(5000);
    }
}
