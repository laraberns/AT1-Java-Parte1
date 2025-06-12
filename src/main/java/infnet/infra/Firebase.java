package infnet.infra;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class Firebase {

    private static Firestore firestore;

    private Firebase(){}

    public static Firestore getFirestore() {
        if(firestore == null) {
            try {
                FileInputStream fis = new FileInputStream("firebase-serviceAccountKey.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(fis))
                        .setDatabaseUrl("")
                        .build();
                FirebaseApp.initializeApp(options);
                firestore = FirestoreClient.getFirestore();
            } catch (IOException e) {
                System.err.println("Erro na conexão com o Firebase");
            }
        }
        return firestore;
    }
}
