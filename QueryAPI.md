# Introduction #

The Query API provides a Builder like syntax to the StackOverflow API. This page gives a few examples of usage of this API.


# Details #

To use the Query API one has to create the appropriate Query object using StackOverflowApiQueryFactory and populate it with parameters.

For example to get the hot questions:
```
StackOverflowApiQueryFactory queryFactory = StackOverflowApiQueryFactory.newInstance("appKey");
List<Question> questions = queryFactory.newQuestionApiQuery()
.withSort(Question.SortOrder.HOT)
.withPaging(new Paging(1, 10))
.withTimePeriod(new TimePeriod(LAST_WEEK_DATE, CURRENT_DATE))
.withFetchOptions(EnumSet.of(FilterOption.INCLUDE_BODY, FilterOption.INCLUDE_COMMENTS))
.list();
```

Similarly to get a list of users sorted by name:
```
StackOverflowApiQueryFactory queryFactory = StackOverflowApiQueryFactory.newInstance("appKey");
List<User> users = queryFactory.newUserApiQuery()
.withSort(User.SortOrder.NAME_ASCENDING)
.list();
```

There are similar Query interfaces for Answers, Comments, Timelines, Badges etc. For a complete list of all available Query interfaces look at the methods of the class StackOverflowApiQueryFactory.
Note that to use the API without the key you will have to create the StackOverflowApiQueryFactory with null application key.
```
StackOverflowApiQueryFactory queryFactory = StackOverflowApiQueryFactory.newInstance(null);

```