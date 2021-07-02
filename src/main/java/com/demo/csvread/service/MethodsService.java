package com.demo.csvread.service;

import com.demo.csvread.data.DataClass;
import com.demo.csvread.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

@Service
public class MethodsService {

    // should be cached
    private DataClass dataClass=new DataClass();

    public ResponseEntity<?> add_csv(MultipartFile file) throws IOException {
        String path = "./attachments_bulk_users";
        File tempFile = null;
        if (!file.isEmpty()) {
            tempFile = new File(path);
            tempFile.mkdir();
            String fileName = file.getOriginalFilename();
            String filePath = path + "/" + fileName;
            InputStream in = new BufferedInputStream(file.getInputStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
            for (int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
            Map<String, Integer> headerMap = new HashMap<>();
            Map<String, User> userMap=dataClass.getUserMap();
            Set<String> managerSet=dataClass.getManagerSet();
            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] lineInArray;
                boolean header = true;
                while ((lineInArray = reader.readNext()) != null) {
                    if(header){
                        int headerSize = lineInArray.length;
                        for(int index=0;index<headerSize;index++){
                            headerMap.put(lineInArray[index].trim().toLowerCase(),index);
                        }
                        header = false;
                        continue;
                    }
                    String name = lineInArray[headerMap.get("Engineer")].trim();
                    String role = lineInArray[headerMap.get("Role")].trim();
                    String managerName = lineInArray[headerMap.get("Manager")].trim();
                    Long featurePoints = Long.valueOf(lineInArray[headerMap.get("Feature Story Points")].trim());
                    Long uatBugPoint = Long.valueOf(lineInArray[headerMap.get("UAT Bug Story Points")].trim());
                    Long prodBugPoint = Long.valueOf(lineInArray[headerMap.get("Prod Buf Story Points")].trim());

                    managerSet.add(managerName);
                    User user=new User();
                    if(userMap.containsKey(name)){
                        user=userMap.get(name);
                    }
                    //set userDetails here





                }
            } catch (Exception ex) {

            }
            dataClass.setUserMap(userMap);
            dataClass.setManagerSet(managerSet);

        }
    // delete local file
        if (tempFile != null && tempFile.listFiles() != null && tempFile.listFiles().length > 0) {
        for (File subFile : tempFile.listFiles()) {
            subFile.delete();
        }
        tempFile.delete();
    }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
