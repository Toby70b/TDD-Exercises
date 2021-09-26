#Calculator Kata TDD Exercise

This repository contains my solution for the [Calculator Kata Exercise](https://osherove.com/tdd-kata-1)

As expected the code has 100% test coverage and every test runs successfully

## Requirements

While the requirements can be found in the link above here is a summarisation

* A method "add()" should exist 
* It should take a String representing one or more non-negative numbers to add seperated by delimiters 
  * Numbers larger than 1000 should be ignored
* It should return an int representing the sum of the numbers <br/>
  * For example "1,2" should return 3
* If null or an empty String is passed as a parameters it should return 0
* If a negative number is encountered an exception should be thrown with the message "negatives not allowed" followed by the negative number passed
  * If multiple negative numbers are encountered then they should all be included in the exception message
* If a new line ("\n") is encountered within the String it should be treated as a delimiter
  * For example "1\n2,3" should return 6
* Custom delimiters can be included in the String
  * These need to be in the format "//[\<customDelimiter>]\n\<numbers>"
  * Custom delimiters can be of any length
  * Multiple custom delimiters can be used. e.g. "["\<customDelimiter1>"]" "["\<customDelimiter2>"]\n\<numbers>"
  * For example "//[*][%]\n1*2%3" should return 6

## Notes
While This was another good exercise in TDD, this was similar to a bad interview question in that it involves 
doing something that no-one would _ever_ do, that is, calculate sums with string values, and then include a number of 
requirements adding questionable features. I suppose at the very least it'll test you as you'd never consider 
this solution in the real world before<br/>

As always there was some ambiguity over requirements, for example should the method handle inner square brackets for delimiters? e.g. [[***]]
I did implement this but may not have been necessary. A problem with these sorts of questionable exercises is that it's hard to apply common sense
<br/>

I spent a couple evenings working on this, and while there are some cases that won't work (can't declare a newline 
delimiter on the delimiter line, though technically this would be sort of invalid as it's already a delimiter) 
I ultimately wasn't enjoying this exercise.  It wasn't an interesting challenge as much as obnoxious. If an ever do come 
back to this solution I can do some refactors to get this cases working 
