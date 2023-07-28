package com.example.stockmarketsim.converters;

import com.example.stockmarketsim.clients.models.News;
import com.example.stockmarketsim.models.responses.ImmutableNewsResponseDTO;
import com.example.stockmarketsim.models.responses.ImmutableNewsResponseWrapper;
import com.example.stockmarketsim.models.responses.NewsResponseDTO;
import com.example.stockmarketsim.models.responses.NewsResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseDtoConverter implements Converter<News, NewsResponseWrapper> {
  @Override
  public NewsResponseWrapper convert(News news) {
    List<NewsResponseDTO> newsResponseDTOS = new ArrayList<>();
    for (News.Result result : news.getResults()) {
      newsResponseDTOS.add(
          ImmutableNewsResponseDTO.builder()
              .authorTitle(result.getAuthor())
              .publisherName(result.getPublisher().getName())
              .articleUrl(result.getArticleUrl())
              .description(result.getDescription())
              .imageUrl(result.getImageUrl())
              .build());
    }
    return ImmutableNewsResponseWrapper.builder()
        .addAllNewsResponse(newsResponseDTOS)
        .nextUrl(news.getNextUrl())
        .build();
  }
}
