package com.ihm.androide.upmc.manekineko.database;

import java.util.ArrayList;

/**
 * Created by HAMDANI on 08/05/2018.
 */

public class ServerTagResponse {
        private String result;
        private String message;
        private ArrayList<String> tags;

        public String getResult() {
            return result;
        }

        public String getMessage() {
            return message;
        }

        public String[] getTags() {
            String[] result = new String[tags.size()];
            for(int i=0; i<tags.size(); i++){
                result[i]=tags.get(i);
            }
            return result;
        }

    }
