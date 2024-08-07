import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    RUSSIAN("ru"), ENGLISH("en");

    private final String shortName;
}
