package bg.sofia.uni.fmi.mjt.warehouse;

import java.time.LocalDateTime;

public record PairWithSubmissionDate<P>(P parcel, LocalDateTime submissionDate) {
};