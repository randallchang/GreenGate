.PHONY: build run

build:
	docker build --tag green_gate \
		-f build/Dockerfile .

run:
	docker run --network host -e SPRING_PROFILES_ACTIVE=local green_gate