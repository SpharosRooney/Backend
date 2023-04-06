package spaland.api.filter.service;

import org.springframework.data.jpa.domain.Specification;
import spaland.api.ProductCategoryList.model.ProductCategoryList;
import spaland.utility.BaseTimeEntity;

public class CategorySpecification {

    public static Specification<ProductCategoryList> equalKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("product").get("name"), '%' + keyword + '%');
    }

    public static Specification<ProductCategoryList> equalCategoryLarge(String categoryLarge) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryLarge").get("name"), categoryLarge);
    }

    public static Specification<ProductCategoryList> equalCategoryMiddle(String categoryMiddle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryMiddle").get("name"), categoryMiddle);
    }

    public static Specification<ProductCategoryList> equalOption(String option) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productOption").get("name"), option);
    }

    public static Specification<ProductCategoryList> equalSeason(String season) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productSeason").get("name"), season);
    }

    public static Specification<ProductCategoryList> equalPrice(String price) {

        Integer left = 0, right = 0;
        if (price.equals("1만원미만")) {
            left = 0;
            right = 9999;
        } else if (price.equals("1만원대")) {
            left = 10000;
            right = 19999;
        } else if (price.equals("2만원대")) {
            left = 20000;
            right = 29999;
        } else if (price.equals("3만원대")) {
            left = 30000;
            right = 39999;
        } else if (price.equals("4만원대")) {
            left = 40000;
            right = 49999;
        } else if (price.equals("5만원이상")) {
            left = 50000;
            right = 9999999;
        }
        Integer finalLeft = left;
        Integer finalRight = right;
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("product").get("price"), finalLeft, finalRight);
    }

    public static Specification<ProductCategoryList> applySort(String sort) {

        if (sort.equals("추천순")) {
            return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.desc(root.get("product").get("salesQuantity")));
                return query.getRestriction();
            };
        } else if (sort.equals("낮은가격순")) {
            return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.asc(root.get("product").get("price")));
                return query.getRestriction();
            };
        } else if (sort.equals("높은가격순")) {
            return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.desc(root.get("product").get("price")));
                return query.getRestriction();
            };
        } else if (sort.equals("신상품순")) {
            return (root, query, criteriaBuilder) -> {
                query.orderBy(criteriaBuilder.desc(root.<BaseTimeEntity>get("updateDate")));
                return query.getRestriction();
            };
        }
        return null;
    }

    public static Specification<ProductCategoryList> equalEvent(String event) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("event").get("name"), event);
    }
}