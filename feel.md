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
    - 왔었는데, 이제는 괜찮다. JPA 에서 Table이라고 꼭 Entity로 선언해야 하는것도 아니고, 벨류타입으로 대부분 가능하다.
    - 에그리거트만 Entity로써의 기능을 온전히 수행하고, 나머지는 벨류타입처럼 사용 (Entity 일지라도)
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
         if (!this.canCreate(store)) throw Ex();
         // 생성자 대입 생략 ..
      }
   }
   
   개인 : 외부 어그리거트를 참조해야 하는 경우는 어떤식으로 할 수 있을까? 
   억지지만, 주문건이 100건이 넘어가는 상태면 만들 수 없다던지
   storeId로 조회해서, Orders(List<Order> Type) 을 canProductCreate 또는 Product/Factory에 넣지 않을까 
   Store에만 의존한다면, 그것도 쉽지 않아보이는데
   
   그런데, 생성자에서 생성 가능한지 여부를 판단하는게 맞는걸까?
   Factory의 인자로, 또는 CreateValidator 같은 컴포넌트로 받아야 할수도 있을것 같다.
   Facotry는 생성만, validator는 벨리데이터만 하는것도 좋아보이긴 한다.
   사실상 모든 클래스만큼 생길수도 있겠네..
   좀이상하다. 
    
   ```
   
----

### JPA 관련
 - DDD에 붙이니 기존 느꼈던 장점보단 JPA가 훨씬 좋아 보인다.
 - 원래 이런 용도로 나온거니 당연하지만, 내가 잘 사용하지 않던(또는 찾아가며 사용했던) 것들이 많네
   ```
   @Embeddable, @Embedded, @EmbeddedId, @AttributeConverter 는 조금 쓰던것들, (@AttributeConverter는 Legacy DB Enum 처리할때 써봤다)
   @AttributeOverrides, @AttributeOverride 는 거의 안쓰던 것들
   알곤 있었지만, 그만큼 불변객체, 값 객체들을 사용하진 않았다는 얘기
   그냥 객체지향 원칙이나, 그동안 공부했던걸 맞춰가려고 알아봤으면 분명 찾을수 있었을텐데 
   ```
 
---
### 삽질
 - User, Order 와 같은 예약어를 사용하다보니 문제 발생
      - Order가 왜 예약어인지 한참 생각했는데 Order By가 있었구나
 - jdbc url param으로 NON_KEYWORDS 사용 하거나, 이름을 변경
 - 이름 변경이 제일 좋은듯해서 이름 바꾸는걸로 사용