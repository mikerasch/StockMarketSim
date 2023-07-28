package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableNewsResponseDTO.class)
public interface NewsResponseDTO {
  String getPublisherName();

  String getAuthorTitle();

  String getArticleUrl();

  String getImageUrl();

  String getDescription();
}
