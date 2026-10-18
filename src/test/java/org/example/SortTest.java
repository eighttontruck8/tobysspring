package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    @Test
    void sort() {
        // 1. 준비 given
        Sort sort = new Sort();

        // 2. 실행 when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 3. 검증 then
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items() {
        Sort sort = new Sort();

        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        Sort sort = new Sort();

        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
