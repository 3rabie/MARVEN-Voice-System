/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marvenspeak;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceRV101 {

    static Size size;
    static String s;
    static List<String> Names = new ArrayList<>();

    public FaceRV101() {

        System.load("/home/rabie/Downloads/opencv-master/build/lib/libopencv_java310.so");

        CascadeClassifier face_cascade = new CascadeClassifier();
        face_cascade.load("/home/rabie/NetBeansProjects/FaceR-v1.0.1/src/data/cascades/lbpcascade_frontalface.xml");
        // face_cascade.load("/home/rabie/Desktop/opencv-haar-classifier-training/classifier/cascade.xml");

        welcomemessage welmsg = new welcomemessage();

        VideoCapture Camera = new VideoCapture(0);
        Camera.open(0);

        if (Camera.isOpened()) {
            //to continue display the  without stop until close  

            Mat frame_original = new Mat();
            Mat frame_gray = new Mat();
            MatOfRect faces = new MatOfRect();

            Camera.read(frame_original);

            size = frame_gray.size();

            Imgproc.cvtColor(frame_original, frame_gray, Imgproc.COLOR_BGRA2GRAY);
            Imgproc.GaussianBlur(frame_gray, frame_gray, new Size(5, 5), 5);

            Imgproc.equalizeHist(frame_gray, frame_gray);

            //load and convert the frames of video to detect faces 
            face_cascade.detectMultiScale(frame_gray, faces);
            Mat fi = new Mat();
            //draw a named rectungular surround detected faces 
            for (Rect rect : faces.toArray()) {

                //  Imgcodecs.imwrite(null, frame_gray);
                fi = frame_gray.submat(rect);

            }
            FaceRec face = new FaceRec();

            Size trainSize = face.loadTrainDir("/home/rabie/NetBeansProjects/FaceR-v1.0.1/src/data/persons");
          

            if (trainSize != null) {
                s = face.predict(fi);
                System.out.println("welcome " + s);
                welmsg.welmsg(s);
                if (s != null) {
                    if (!Names.contains(s)) {
                        Names.add(s);
                        //   welmsg.speak("Welcome " + s);
                    }

                }
            }
            Camera.release();

        } else {
            System.out.println("Couldn't connect to Camera");
        }
    }

}
