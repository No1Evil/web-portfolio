package dev.tsumakov.infrastructure.shared.util;

import com.github.f4b6a3.uuid.UuidCreator;
import dev.tsumakov.domain.shared.util.UuidGenerator;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UuidGeneratorImpl implements UuidGenerator {

  @Override
  public UUID generate() {
    return UuidCreator.getTimeOrderedEpoch();
  }
}