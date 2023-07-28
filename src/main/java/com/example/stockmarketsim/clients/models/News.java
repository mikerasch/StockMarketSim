package com.example.stockmarketsim.clients.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
  @JsonProperty(value = "results")
  public List<Result> results;

  @JsonProperty("status")
  public String status;

  @JsonProperty("request_id")
  public String requestId;

  @JsonProperty("count")
  public Integer count;

  @JsonProperty("next_url")
  public String nextUrl;

  @Getter
  @Setter
  public static class Publisher {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "homepage_url")
    private String homepageUrl;

    @JsonProperty(value = "logo_url")
    private String logoUrl;

    @JsonProperty(value = "favicon_url")
    private String faviconUrl;
  }

  @Getter
  @Setter
  public static class Result {
    @JsonProperty("id")
    public String id;

    @JsonProperty("publisher")
    public Publisher publisher;

    @JsonProperty("title")
    public String title;

    @JsonProperty("author")
    public String author;

    @JsonProperty("published_utc")
    public String publishedUtc;

    @com.fasterxml.jackson.annotation.JsonProperty(value = "article_url")
    public String articleUrl;

    @JsonProperty("tickers")
    public List<String> tickers;

    @JsonProperty("amp_url")
    public String ampUrl;

    @JsonProperty("image_url")
    public String imageUrl;

    @JsonProperty("description")
    public String description;

    @JsonProperty("keywords")
    public List<String> keywords;
  }
}
