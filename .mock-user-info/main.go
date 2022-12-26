package main

import (
	"fmt"
	"math/rand"
	"net/http"
)

func getUserStatus(w http.ResponseWriter, req *http.Request) {
	randInt := rand.Intn(2)
	fmt.Println("User cpf ", req.URL.Path)
	if randInt == 0 {
		fmt.Fprintf(w, "UNABLE_TO_VOTE")
		return
	}
	fmt.Fprintf(w, "ABLE_TO_VOTE")
}

func main() {
	http.HandleFunc("/", getUserStatus)
	http.ListenAndServe(":8080", nil)
}
