package blog.econovation.tcono.domain.post;

import java.util.Arrays;

public enum MainCategory {
    TECH, CULTURE, TROUBLE_SHOOTING;
    private static final String NOT_FOUND_MAINCATEGORY_MESSAGE ="해당 카테고리의 게시물이 없습니다";
    public static MainCategory getMainCategory(MainCategory mainCategory) {
        return Arrays.stream(MainCategory.values())
                .filter(x -> x.equals(mainCategory))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MAINCATEGORY_MESSAGE));
    }
}