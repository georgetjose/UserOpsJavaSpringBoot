package com.user.userops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class ReqresUser {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private ArrayList<ReqresUserData> data = new ArrayList<>();
    private Support support;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Data
    public static class ReqresUserData {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Data
    public static class Support {
        private String url;
        private String text;
    }
}
