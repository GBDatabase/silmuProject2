(1)  action태그 지우고 csrf 토큰 생성

<u>애가 띄워질때 없었던 url을 그대로 호출</u>

  

# question_Form.html
```

    < form th:object="${questionForm}" method="post"> <!-- 값없이 get방식과 값있게 post 방식 -->

        <!-- csrf 토큰 생성 -->

        <input type="hidden" th:name="${_csrf.parameterName}"

            th:value="${_csrf.token}" >
```

  

(2) 서비스에 등록하고 controller에서

  

# questionService.java
```

    public void modify(Question question, String subject, String content) {

        question.setSubject(subject);

        question.setContent(content);

        question.setModifyDate(LocalDateTime.now());

        this.questionRepository.save(question);

    }

```

  
  
  

(3)  유효성 검증 후 글쓴이와 로그인 정보가 같으은지 확인해주고 맞다면
# questionController.java
```

    @PreAuthorize("isAuthenticated()")

    @PostMapping("/modify/{id}")

    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal, //폼클래스를 입력하여 유효성 검증

            @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {

            return "question_form";

        }

        Question question = qService.getQuestion(id);

        if (!question.getAuthor().getUsername().equals(principal.getName())) { //글쓴이하고 로그인한 정보의 사용자가 동일해야함

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");

        }

        this.qService.modify(question, questionForm.getSubject(), questionForm.getContent()); //입력받는 곳 :questionForm에

        return String.format("redirect:/question/detail/%s", id); //detail페이지에 id를 넘겨서 detail페이지에서 확인가능하게 함

    }          
```



(3) 자바스크립트에서 확인 누르면 넘어갈 수있는 url을 적으면 바로삭제가 가능하도록 함

- 수정이랑 다른부분 :  <a href="javascript:void(0);"
- data-uri 는 스크립트에서 띄워서 삭제하겠다 할때 이 요청정보를 넘겨서 사용하겠다 할때
- delete 클래스를 따로 줌 :  class="delete btn btn-sm btn-outline-secondary"
- 수정과 달리 data-uri를 사용ㅎ서 어딘가에 저장을 함

  


    <!-- 질문 내용 삭제 버튼 추가 -->
```
    <div class="my-3">

    <a href="javascript:void(0);"

        th:data-uri="@{|/question/delete/${question.id}|}"

        class="delete btn btn-sm btn-outline-secondary"

        sec:authorize="isAuthenticated()"

        th:if="${question.author != null and

        #authentication.getPrincipal().getUsername() == question.author.username}"

        th:text="삭제"></a>

    </div>
```

  
  

(4) layout

  
```
<th:block layout:fragment="script"></th:block>
```
  
  

 (5) question_detail에 javascript를 적어준다

```

<-- javascript 처리부분을 내가 타이핑 한거 오류난거 알아서 아래걸로 함-->

 <script layout:fragment="script" type="text/javascript">

    const delete_elements = document.getElementByClassName("delete") //delete라고 등록되어있는 클래스를 가져와서 애를 처리하도록 함

    Array.from(delete_elements).formEach(funtion(element)) {

        element.addEventListener("click",funtion() {} )

            if (confirm("정말로 삭제하겠습니까?")) {

                location.hrdf = this.dataset.uri //확인눌렀을때 location.hrdf로 넘겨주는데 위에 나온 dataset.uri로 넘겨줌

            };

        };    

```

  

```

<!-- 교과서 -->

<script layout:fragment="script" type='text/javascript'>

    const delete_elements = document.getElementsByClassName("delete");

    Array.from(delete_elements).forEach(function (element) {

        element.addEventListener('click', function () {

            if (confirm("정말로 삭제하시겠습니까?")) {

                location.href = this.dataset.uri;

            };

        });

    });

```

  
  

(6) questionService.java에 다음과 같은 코드를 넣는다

```

    public void delete(Question question) {

        this.questionRepository.delete(question);

    }

```

  

(7) questionController.java에 다음과 같은 코드를 넣는다

  

```

@PreAuthorize("isAuthenticated()")

    @GetMapping("/delete/{id}")

    public String questionDelete(@PathVariable("id") int id,

                                Principal principal ) { //로그인한 정보 principal로 받음

        Question question = qService.getQuestion(id);

        if (!question.getAuthor().getUsername().equals(principal.getName())) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");

        }

        qService.delete(question);

        return "redirect:/";

    }

```