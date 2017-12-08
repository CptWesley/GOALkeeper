# Goal Analyzer

Goal Analyzer is a static tool which can be used on multi-agent systems written in the 
[GOAL](https://goalapl.atlassian.net/wiki/spaces/GOAL/overview) agent-based programming language
developed at the 
[TU Delft Interactive Intelligence Group](https://www.tudelft.nl/ewi/over-de-faculteit/afdelingen/intelligent-systems/interactive-intelligence/).
 
The tool aims to apply standard software metrics to an agent based programming 
language for programming cognitive agents in order to improve code quality and 
reduce potential bugs.
 
## Usage

The jar can be used by running the command:  
`java -jar {name of jar} -rules={relative-path-to-rules-file} -mas={relative-path-to-mas2g-file}`

This jar can also be used on continuous integration servers as it will return an appropriate exit
code upon finishing which a CI service is able to interpret for letting the build pass or fail.  

More information can be found on the [wiki](https://github.com/CptWesley/goal-analyzer/wiki/Running-Goal-Analyzer). 

***

Created by: [Wesley Baartman](https://github.com/cptwesley) and [Cedric Willekens](https://github.com/ceddy4395)  
Under the supervision of: [Vincent Koeman](https://github.com/Venorcis)