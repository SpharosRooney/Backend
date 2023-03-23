package spaland.products.model_v2;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpecificationV2 {

    public static Specification<Filter> equalPrice(Long price){
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("price"), price);

    }

    public static Specification<Filter> equalSeason(Long season){
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("season"), season);
    }

    public static Specification<Filter> equalCategory(Long category){
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Filter> equalVolume(Long volume){
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("volume"), volume);
    }
}
