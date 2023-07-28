package com.example.stockmarketsim.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableNewsResponseWrapper.class)
public interface NewsResponseWrapper {
  List<NewsResponseDTO> getNewsResponse();

  String getNextUrl();
}
