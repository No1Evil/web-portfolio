package dev.tsumakov.domain.service;

import dev.tsumakov.domain.model.AssetPath;
import java.util.UUID;

public class AssetPathGenerator {
  public AssetPath generatePath(String fileName, String category){
    String extension = fileName.substring(fileName.lastIndexOf('.'));
    String uniqueName = category + "_" + UUID.randomUUID() + extension;
    return new AssetPath("/uploads/" + uniqueName);
  }
}
