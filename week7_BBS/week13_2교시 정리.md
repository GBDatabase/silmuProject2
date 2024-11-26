#답변도 수정해봄

(1) 	question_detail에 
```
<div class="my-3"> <!-- 마진 3 -->
    <a th:href="@{|/answer/modify/${answer.id}|}"
        class="btn btn-sm btn-outline-secondaty"
        sec:authorize="isAuthenticated()"
        th:if="${answer.author != null and 
            #authentication.getPrinciapl().getUsername() == answer.author.username}"
    th:text="수정">
    </a>
```


(2) answerService에 Modify()
```
//답변 수정
public void modify(Answer answer,String content) {
    answer.setContent(content);
    answer.setModifyDate(LocalDateTime.now());
    answerRepository.save(answer);
}
```
	


(3) AnswerController에 answerModify() 를 작성함

```
@PreAuthorize("isAuthenticated()")
@PostMapping("/modify/{id}")
public String answerModify(@Valid AnswerForm answerForm, 
                                    @PathVariable("id") Integer id,					
                                    Principal principal,
                                    ) {

    Answer answer = answerService.getAnswer(id);

    if (!answer.getAuthor().getUsername().equals(principal.getName())) { //answer에 getAuthor한게 = principal 객체의 getName한 로그인정보와 같아야함
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."); //
    }
    

    answerForm.setContent(answer.getContent());
    return "answer_form";
}
```
	
	
	
(4) anwer_form 생성후 작성
- action은 안씀
- form arrors 

```
<html layout:decorate="~{layout}">
<div layout:fragment="content" class="container-my-3">
	<h5>답변 수정</h5>
	<form th:object="${answerForm}" method="post"> <!-- //동일한 아이디값이 재요청됨 -->
		<input  type="hidden" 
				th:name="${_csrf.parameterName}"
				th:value="${_csrf.token}" /> <!-- //action이 없어지면 ~~하기때문에 "hidden 넣어주기" -->
		<div th:replace="~{form_errors :: formErrorsFragment}"></div>
		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<textarea th:field="*{content}" class="form-control" rows="10"></textarea>
		</div>
		<input type="submit" value="저장하기" class="btn btn-primary my-2">
	</form>
</div>
```

(5) answerController의 answerModify를 작성한다
```
@PreAuthorize("isAuthenticated()")
@PostMapping("/modify/{id}")
public String answerModify(@Valid AnswerForm answerForm, 
        @PathVariable("id") Integer id,					
        Principal principal.
        BindingResult bindingResult 
        ) {
    
    if (bindingResult.hasErrors()) {
        return "answer_form";
    }
    
    Answer answer = this.answerService.getAnswer(id);
    if (!answer.getAuthor().getUsername().equals(principal.getName())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    }
    this.answerService.modify(answer, answerForm.getContent());
    return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
}
```
	
	
(6) 답변 수정 삭제버튼의 삭제버튼 내용 추가
```
<!--  답변 수정, 삭제 버튼 -->
    <div class="my-3"> <!-- 마진 3 -->
        <a th:href="@{|/answer/modify/${answer.id}|}"
            class="btn btn-sm btn-outline-secondaty"
            sec:authorize="isAuthenticated()"
            th:if="${answer.author != null and 
                #authentication.getPrinciapl().getUsername() == answer.author.username}"
        th:text="수정">
        </a>
        
        <a href="javascript:void(0);"
        <a th:href="@{|/answer/delete/${answer.id}|}"
            class="btn btn-sm btn-outline-secondaty"
            sec:authorize="isAuthenticated()"
            th:if="${answer.author != null and 
                #authentication.getPrinciapl().getUsername() == answer.author.username}"
        th:text="삭제">
        </a>	
    div>
```	
		


(7) 수정일자 표시, 답변 수정일자 표시 를 question-detail.html에서 수정함 (잘 넣기)

```
<!-- question_detail.html -->
<!-- 수정일자 ㅍ시-->
<div th:if="${question.modifyDate != null}" class="badge bg-light text-dark p-2 text-start mx-3">
    <div class="mb-2">modified at</div>
    <div th:text="${#temporals.format(question.modifyDate, 'yyyy-MM-dd HH:mm')}"></div>
</div>
```	
			
```
<!-- 답변수정일자 표시 -->>
<div th:if="${question.modifyDate != null}" 
			class="badge bg-light text-dark p-2 text-start mx-3">
		<div class="mb-2">modified
		</div>
		<div th:text="${#temporals.format(answer.modifyDate, 'yyyy-MM-dd HH:mm')}"></div> <!-- //#temporals.format를 사용함 -->
</div>
```		