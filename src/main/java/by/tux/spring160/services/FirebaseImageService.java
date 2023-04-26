package by.tux.spring160.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FirebaseImageService {
    private Storage storage = StorageOptions.getDefaultInstance().getService();

    public String save(MultipartFile multipartFile) throws Exception{
        String imageName = String.valueOf(System.currentTimeMillis());
        BlobId blobId = BlobId.of("spring160-a4a1f.appspot.com", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(multipartFile.getContentType())
                .build();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json/spring160-a4a1f-firebase-adminsdk-nrtsy-fc40596289.json");

        storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.
//                        fromStream(new FileInputStream("spring160-a4a1f-firebase-adminsdk-nrtsy-fc40596289.json")))
                        fromStream(inputStream))
                .build()
                .getService();

        storage.create(blobInfo, multipartFile.getInputStream());
        return imageName;
    }
    public String getPhotoUrl(String name){
        return "https://firebasestorage.googleapis.com/v0/b/spring160-a4a1f.appspot.com/o/" + name + "?alt=media&token=b8176c03-b4ef-41ee-b4db-a08ed403aef3";
    }
}
