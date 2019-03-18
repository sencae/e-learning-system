package com.e_learning_system.googleApi;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveService.class);
    @Value("${google.service_account_email}")
    private String serviceAccountEmail;
    @Value("${google.application_name}")
    private String applicationName;
    @Value("${google.folder_id}")
    private String folderId;
    @Value("${google.service_account_key}")
    private String serviceAccountKey;

    public java.io.File multipartToFile(MultipartFile multipart)
    {
        try {
            java.io.File convFile = new java.io.File(multipart.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipart.getBytes());
            fos.close();
            return convFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Drive getDriveService(){
        Drive service = null;
        try{
            URL resourse = GoogleDriveService.class.getResource("/"+this.serviceAccountKey);
            java.io.File key = Paths.get(resourse.toURI()).toFile();
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            GoogleCredential googleCredential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key).build();
            service = new Drive.Builder(httpTransport,jsonFactory,googleCredential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(googleCredential).build();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return service;
    }
    public File uploadFile(String fileName, java.io.File fileUpload, String mimeType){
        File file = new File();
        try{
            File fileMetaData = new File();
            fileMetaData.setMimeType(mimeType);
            fileMetaData.setName(fileName);
            fileMetaData.setParents(Collections.singletonList(folderId));

            FileContent fileContent = new FileContent(mimeType,  fileUpload);
            file = getDriveService().files().create(fileMetaData,fileContent)
                    .setFields("id,webContentLink,webViewLink").execute();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        return file;
    }
    public List<File> getFilesList() {
        try {
            FileList result = getDriveService().files().list()
                    .setPageSize(10)
                    .setFields("nextPageToken, files(id, name,webContentLink)")
                    .execute();
            List<File> files = result.getFiles();
            if (files == null || files.isEmpty()) {
                System.out.println("No files found.");
            } else {
                System.out.println("Files:");
                for (File file : files) {
                    System.out.printf("%s (%s) %s\n", file.getName(), file.getId(), file.getWebContentLink());
                }
            }
            return files;
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
    public boolean deleteFile(Drive service, String fileId) {
        try {
            service.files().delete(fileId).execute();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
            return false;
        }
    }
    public void downloadFile(String fileId) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        getDriveService().files().get(fileId).executeMediaAndDownloadTo(outputStream);

    }

    //1rl_G74flZESBrh8-jKCemT36vxsfs1Dp
//    Getting started (0Bw87nmOp4uLsc3RhcnRlcl9maWxl)


}
