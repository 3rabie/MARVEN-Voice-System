package marvenspeak;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;

public class MARVENspeak {

    public static void main(String[] args) throws IOException {

        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath("/home/rabie/Downloads/sphinx4-5prealpha-src/sphinx4-data/src/main/resources/edu/cmu/sphinx/models/en-us/en-us/");
        configuration
                .setDictionaryPath("/home/rabie/Downloads/TAR4062/4062.dic");
        //"/home/rabie/Downloads/TAR7416/7416.dic");
        configuration
                .setLanguageModelPath("/home/rabie/Downloads/TAR4062/4062.lm");
//                        "/home/rabie/Downloads/TAR7416/7416.lm");
        configuration
                .setSampleRate(16000);
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        // Start recognition process pruning previously cached data.
        recognizer.startRecognition(true);
        SpeechResult result;
                        welcomemessage wm = new welcomemessage();
        while ((result = recognizer.getResult()) != null) {
            
            System.out.format("you say: %s\n", result.getHypothesis());
            if (result.getHypothesis().equals("GO FORWARD")|result.getHypothesis().equals("FORWARD")) {
                System.out.println("sir yes sir,moving forward");
                wm.speak("sir yes sir,moving forward");
            } else if (result.getHypothesis().equals("GO BACK")|result.getHypothesis().equals("BACK")) {
                System.out.println("oooops, be carefule marven moving back");
                wm.speak("oooops, be carefule marven moving back");
            }else if (result.getHypothesis().equals("FACE MODE")){
                FaceRV101 FR = new FaceRV101();
            }else if (result.getHypothesis().equals("WELCOME MARVEN")|result.getHypothesis().equals("WELCOME")){
                wm.speak("welcome sir can i help you");
            }
            result = null;

        }
        
        // Pause recognition process. It can be resumed then with startRecognition(false).
        recognizer.stopRecognition();
    }

}
