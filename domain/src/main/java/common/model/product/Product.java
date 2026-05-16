package common.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
    public class Product  {

        private Long id ;
        private String name;
        private String description;
        private String href ;
        private Boolean isBundle;
}
