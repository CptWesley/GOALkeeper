# goal-analyzer

The goal-analyzer is a static tool which can be used on agent written in the language
 [GOAL](https://goalapl.atlassian.net/wiki/spaces/GOAL/overview) which is developed at the 
 [TU Delft interactive intelligence group](https://www.tudelft.nl/ewi/over-de-faculteit/afdelingen/intelligent-systems/interactive-intelligence/).
 
 The tool aims to apply software metrics for standard languages, such as Java, to an 
 agent programming language for programming cognitive agents. 
 
## Usage

The jar can be used by running the command:  
`java -jar {name of jar} -rules={relative-path-to-rules-file} -mas={relative-path-to-mas2g-file}`

This jar can also be used on continuous integration servers as it will return an exit code upon finishing 
which the CI is able to interpret to let the build pass or fail. 

***

created by: [Wesley Baartman](https://github.com/cptwesley) and [Cedric Willekens](https://github.com/ceddy4395)  
under the supervision of: [Vincent Koeman](https://github.com/Venorcis)