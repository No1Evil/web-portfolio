package dev.tsumakov.domain.model;

import java.util.Objects;

public record AssetPath(String value) {

  public AssetPath {
    Objects.requireNonNull(value, "Path value cannot be null");

    if (value.isBlank()) {
      throw new IllegalArgumentException("Path value cannot be blank");
    }

    if (!value.startsWith("/uploads/")) {
      throw new IllegalArgumentException("Asset path must start with /uploads/");
    }
  }
}
