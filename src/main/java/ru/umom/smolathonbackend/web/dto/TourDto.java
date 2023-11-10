package ru.umom.smolathonbackend.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.context.request.RequestScope;

import java.util.List;

public class TourDto {
    private interface Id {
        String id();
    }

    private interface Title {
        String title();
    }

    private interface Description {
        String description();
    }

    private interface Contact {
        String contact();
    }

    private interface Company {
        String company();
    }

    private interface PhotoId {
        @JsonProperty("photo_id")
        String photoId();
    }

    private interface PreferencesId {
        @JsonProperty("preferences_id")
        List<String> preferencesId();
    }

    public static class Request {
        public record Create(
                @NotBlank
                String title,
                String description,
                @NotBlank
                String contact,
                @NotBlank
                String company,
                String photoId,
                List<String> preferencesId) implements Title, Description, Contact, Company, PhotoId, PreferencesId {
        }

        public record Update(
                @NotBlank
                String id,
                @NotBlank
                String title,
                String description,
                @NotBlank
                String contact,
                @NotBlank
                String company,
                String photoId,
                List<String> preferencesId) implements Id, Title, Description, Contact, Company, PhotoId, PreferencesId {
        }

        public record Delete(String id) implements Id {
        }

        public record Get(String id) implements Id {
        }

    }

    public static class Response {
        public record BaseResponse(
                String id,
                String title,
                String description,
                String contact,
                String company,
                String photoId,
                List<String> preferencesId) implements Id, Title, Description, Contact, Company, PhotoId, PreferencesId {
        }
    }

}
