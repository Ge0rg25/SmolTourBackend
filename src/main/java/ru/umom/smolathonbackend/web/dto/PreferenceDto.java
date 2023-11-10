package ru.umom.smolathonbackend.web.dto;

import jakarta.validation.constraints.NotBlank;

public class PreferenceDto {
    private interface Id {
        String id();
    }

    private interface Title {
        String title();
    }

    public static class Request {
        public record AddUserPreference(@NotBlank String id) implements Id {
        }

        public record DeleteUserPreference(@NotBlank String id) implements Id {
        }
    }

    public static class Response {
        public record BaseResponse(String id, String title) implements Id, Title {
        }
    }
}
