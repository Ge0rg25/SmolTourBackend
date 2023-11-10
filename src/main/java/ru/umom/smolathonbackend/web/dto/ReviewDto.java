package ru.umom.smolathonbackend.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewDto {
    private interface Id {
        String id();
    }

    private interface Rating {
        int rating();
    }

    private interface Message {
        String message();
    }

    private interface OwnerName {
        @JsonProperty("owner_name")
        String ownerName();
    }

    private interface OwnerId {
        @JsonProperty("owner_id")
        String ownerId();
    }

    private interface TourId {
        @JsonProperty("tour_id")
        String tourId();
    }


    public static class Request {
        public record Create(@NotNull int rating, @NotBlank String message,
                             @NotBlank String tourId) implements Rating, Message, TourId {
        }

        public record Delete(@NotBlank String id) implements Id {
        }

        public record GetAllByTourId(@NotBlank String tourId) implements TourId{}
    }

    public static class Response {
        public record BaseResponse(String id, int rating, String message, String ownerName, String tourId,
                                   String ownerId) implements Id, Rating, Message, OwnerName, OwnerId, TourId {
        }
    }
}
