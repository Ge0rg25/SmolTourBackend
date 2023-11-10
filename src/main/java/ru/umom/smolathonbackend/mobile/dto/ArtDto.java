package ru.umom.smolathonbackend.mobile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class ArtDto {
    private interface Id {
        String id();
    }

    private interface OwnerId {
        @JsonProperty("owner_id")
        String ownerId();
    }

    private interface Title {
        String title();
    }

    private interface Description {
        String description();
    }

    private interface ImageId {
        @JsonProperty("image_id")
        String imageId();
    }

    private interface Lat {
        double lat();
    }

    private interface Lon {
        double lon();
    }


    public static class Request {
        public record Create(
                @NotBlank
                String title,
                @NotBlank
                String description,
                String imageId,
                @NotNull
                double lat,
                @NotNull
                double lon
        ) implements Title, Description, ImageId, Lat, Lon {
        }

        public record Delete(@NotBlank String id) implements Id {
        }
    }

    public static class Response {
        public record BaseResponse(String id, String ownerId, String title, String description, String imageId,
                                   double lat,
                                   double lon) implements Id, OwnerId, Title, Description, ImageId, Lat, Lon {
        }
    }
}
