# (1) AnswerController의 delete 부분 추가


	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}





# (2) answerSerice에 이거 넣어주기

	//답변 삭제
	public void delete(Answer answer) {
		answerRepository.delete(answer);
	}
	
	
# 1. 추천기능넣기

- 좋아요 나 추천 등과 같은 표시 만들기 
- vote()나 voter 만들기
- 질문하나에 추천여러개 가능 (내가 할 수 있고 사용자가 할 수 있고) 
- 여러질문에 추천 여러개 할 수 있어서 many to one인데 질문입장에서 여러명이 추천할 수 있고, 사용자 입장에서도 여러명 가능
- 왜 set(집합) 자료구조를 쓸까 : 순서가 필요없고 들어오는게 중요. 중복성과 유일성 만족하는 자료구조여서

(1) question.java에

	@ManyToMany
    Set<SiteUser> voter;
    
    
(2) answer.java에 

	@ManyToMany
    Set<SiteUser> voter;
    
    
(3) question_detail에 

<div class="my-3">
	<a href="javascript:void(0);"
	  class="recommend btn btn-sm btn-outline-secondary"
		th:data-uri="@{|/answer/vote/${answer.id}|}">
		추천
		<span class="badge rounded-pill bg-success" 
		th:text="${#lists.size(answer.voter)}"></span>
	</a>
</div>
				


//몇명이나 추천했는지 보여주는
<span class="badge rounded-pill bg-success" th:text="${#lists.size(answer.voter)}"></span>
    
    
(4) question_detail에 아래에


	const recommend_elements = document.getElementsByClassName("recommend");
	Array.from(delete_elements).forEach(function (element) {
		element.addEventListener('click', function () {
			if (confirm("정말로 추천하시겠습니까?")) {
				location.href = this.dataset.uri;
			};
		});
	});
	
	---
	
						th:data-uri="@{|/question/vote/${answer.id}|}">
						
						
	이게들어감
	
	
(5) question service에 vote()

	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		qRepository.save(question);
	}
	
	
	
(6) questionController에

@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal, 
							@PathVariable("id") int id) {
		Question question = qService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName()); //user값은 principal에서 받아옴
		qService.vote(question, siteUser);
		return String.format("redirect:/question/detail/%s", id);
	}
		
	
	