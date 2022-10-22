cmake_minimum_required(VERSION 3.0.0)
project(Question_2 VERSION 0.1.0)
message("CMAKE_PROJECT_NAME = ${CMAKE_PROJECT_NAME}")


# Bring the headers, such as Student.h into the project
file(GLOB MY_HEADER_FILES include/*.hxx)

file(GLOB MY_SOURCE_FILES src/*.cxx)


add_executable(Question_2
	${MY_HEADER_FILES}
	${MY_SOURCE_FILES}
)


set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})

include(CPack)
