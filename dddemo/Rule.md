### Domain Rule


###OTHER

 - Drools -> 룰관련, 뭔가 신기하다
 - DIP -> Dependency Inversion Principal
   ```
    # JPA Repository Interface 처럼 저수준 구현을 추상화해서 사용하면 저수준을 추상화 시킬수 있다. 
    # 당연히 테스트 편하고 교체도 편함
    # 인터페이스를, 저수준 기준으로 생각하는게 아니라 기능 관점으로 생각해야함
    # 쉽지않은데
   ```
 - 에그리거트 -> Jpa Entity모델과, 항상 살짝 혼동이 온다.
 - 에그리거트는, 하나 대부분 두개의 엔티티라는데, 여기서는 DB 엔티티를 얘기하는건가? 
 - 엔티티를 만드는 것을 어디다 둘것인가
   ```
    # 책에서는 Store -- Product 에서 Store의 method 로 Product를 만들었다 
    # Product createProduct(ProductInfo..);
    # store의 여러 정보를 사용하기 위함인데, 별도 클래스 ProductFactory에다가 만들거나, Product의 생성자로 처리하는건 문제가 있을까? 
   ```
   ``` java
   V1
   public class Store {
      private String storeId;
      private boolean productCreate;
      
      public Product createProduct(ProductInfo productInfo) {
         if (!canProductCreate()) throw Ex();
         return ProductFacotry.create(this.storeId, productInfo); 
      }
   }
   
   V2 (아래와 유사, 또는 Factory에서 canCreate 처리 후 옮김)
   public class Product {
      private String productId;
      private ProductInfo info;
      private String storeId;
      
      public Product(Store store, ProductInfo productInfo) {
         if (store.canProductCreate()) throw Ex();
         // 생성자 대입 생략 ..
      }
   }
   
   개인 : 외부 어그리거트를 참조해야 하는 경우는 어떤식으로 할 수 있을까? 
   억지지만, 주문건이 100건이 넘어가는 상태면 만들 수 없다던지
   storeId로 조회해서, Orders(List<Order> Type) 을 canProductCreate 또는 Product/Factory에 넣지 않을까 
   Store에만 의존한다면, 그것도 쉽지 않아보이는데
   ```
   
