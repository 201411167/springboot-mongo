package smu.computers.contesthelper.domain.category;

import lombok.Builder;
import lombok.Data;

@Data
public class Category {
    String category_name;
    double score;

    @Builder
    public Category(String category_name, double score) {
        this.category_name = category_name;
        this.score = score;
    }
}
