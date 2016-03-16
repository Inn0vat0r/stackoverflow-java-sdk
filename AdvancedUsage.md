# Introduction #

This page describes the advanced usage of the API.


# Details #

### Paging ###
All the lists returned by the Query APU or the Facade API are instances of PagedList which exposes methods that help in pagination of the result.
For Query API
```
StackOverflowApiQueryFactory queryFactory = StackOverflowApiQueryFactory.newInstance("appKey");
QuestionApiQuery query = queryFactory.newQuestionApiQuery();
PagedList<Question> questions = query
.withSort(Question.SortOrder.HOT)
.withPaging(new Paging(1, 10))
.withTimePeriod(new TimePeriod(LAST_WEEK_DATE, CURRENT_DATE))
.withFetchOptions(EnumSet.of(FilterOption.INCLUDE_BODY, FilterOption.INCLUDE_COMMENTS))
.list();
while (questions.getTotal() > (questions.getPageSize() * questions.getPage())) {
 questions = query.withPaging(new Paging(questions.getPage() + 1, questions.getPageSize())).list();
 // do something with questions
}
```

### Throttle Limit ###

### API Providers ###