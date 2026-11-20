
const sendButton = document.getElementById("sendButton");

sendButton.addEventListener("click", sendMessage);

async function sendMessage() {

    const promptInput = document.getElementById("promptInput");
    const aiResponse = document.getElementById("aiResponse");
    const loadingMessage = document.getElementById("loadingMessage");

    const message = promptInput.value.trim();

    if (!message) {
        alert("Please enter a message.");
        return;
    }

    try {

        // Show loading message
        loadingMessage.classList.remove("hidden");
        sendButton.disabled = true;

        // Call Spring Boot API
        const response = await fetch("/api/ai/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                message: message
            })
        });

        if (!response.ok) {
            throw new Error("API request failed: " + response.status);
        }

        // Convert JSON response into a JavaScript object
        const data = await response.json();

        // Display AI response
        aiResponse.textContent = data.response;

        // Update token statistics
        document.getElementById("promptTokens").textContent =
            data.promptTokens ?? "-";

        document.getElementById("completionTokens").textContent =
            data.completionTokens ?? "-";

        document.getElementById("totalTokens").textContent =
            data.totalTokens ?? "-";

        document.getElementById("responseTime").textContent =
            data.responseTimeMs + " ms";

    } catch (error) {

        console.error("Error:", error);
        aiResponse.textContent =
            "Something went wrong while communicating with the AI service.";

    } finally {

        // Hide loading message and enable button
        loadingMessage.classList.add("hidden");
        sendButton.disabled = false;

    }
}
