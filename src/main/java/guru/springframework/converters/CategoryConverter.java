package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

public class CategoryConverter {

    @Component
    public static class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
        @Synchronized
        @Nullable
        @Override
        public Category convert(CategoryCommand source) {
            if(source == null){
                return null;
            }

            final Category ret = new Category();
            ret.setId(source.getId());
            ret.setDescription(source.getDescription());
            return ret;
        }
    }

    @Component
    public static class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
        @Synchronized
        @Nullable
        @Override
        public CategoryCommand convert(Category source) {
            if(source == null){
                return null;
            }

            final CategoryCommand ret = new CategoryCommand();
            ret.setId(source.getId());
            ret.setDescription(source.getDescription());
            return ret;
        }
    }
}
