// src/main/resources/static/app.js
// Simple UI client for Rabbit backend (Spring Boot)

// Generic JSON API helper
async function api(path, opts = {}) {
    const headers = {
      "Content-Type": "application/json",
      ...(opts.headers || {})
    };
  
    const res = await fetch(path, {
      method: opts.method || "GET",
      headers,
      body: opts.body
    });
  
    // Some endpoints return empty body (e.g., signup)
    const text = await res.text();
    let body = null;
    try {
      body = text ? JSON.parse(text) : null;
    } catch {
      body = text; // plain text fallback
    }
  
    if (!res.ok) {
      // Try to extract a meaningful error
      const msg =
        (body && typeof body === "object" && body.message) ? body.message :
        (typeof body === "string" && body.trim().length > 0) ? body :
        res.statusText;
  
      throw new Error(`${res.status} ${msg}`);
    }
  
    return body;
  }
  
  function setMsg(id, msg, ok = true) {
    const el = document.getElementById(id);
    if (!el) return;
    el.className = ok ? "ok" : "error";
    el.textContent = msg;
  }
  
  function escapeHtml(s) {
    return String(s)
      .replaceAll("&", "&amp;")
      .replaceAll("<", "&lt;")
      .replaceAll(">", "&gt;")
      .replaceAll('"', "&quot;")
      .replaceAll("'", "&#039;");
  }
  
  async function loadFeed() {
    const feedEl = document.getElementById("feed");
    if (!feedEl) return;
  
    feedEl.innerHTML = "<div class='muted'>Loading...</div>";
  
    const items = await api("/api/posts/feed", { method: "GET" });
  
    if (!items || items.length === 0) {
      feedEl.innerHTML = "<div class='muted'>No posts yet.</div>";
      return;
    }
  
    feedEl.innerHTML = items.map(p => `
      <div class="card">
        <div><b>@${escapeHtml(p.username)}</b></div>
        <div>${escapeHtml(p.content)}</div>
        <div class="muted">${new Date(p.createdAt).toLocaleString()}</div>
      </div>
    `).join("");
  }
  
  async function signup() {
    const username = (document.getElementById("suUser")?.value || "").trim();
    const password = document.getElementById("suPass")?.value || "";
  
    if (!username) {
      setMsg("signupMsg", "Username is required.", false);
      return;
    }
    if (!password || password.length < 6) {
      setMsg("signupMsg", "Password must be at least 6 characters.", false);
      return;
    }
  
    await api("/api/auth/signup", {
      method: "POST",
      body: JSON.stringify({ username, password })
    });
  
    setMsg("signupMsg", "Signup successful ✅");
    const postUserEl = document.getElementById("postUser");
    if (postUserEl) postUserEl.value = username;
  }
  
  async function createPost() {
    const username = (document.getElementById("postUser")?.value || "").trim();
    const content = document.getElementById("postContent")?.value || "";
  
    if (!username) {
      setMsg("postMsg", "Set X-USER (username) first.", false);
      return;
    }
    if (!content.trim()) {
      setMsg("postMsg", "Post content cannot be empty.", false);
      return;
    }
  
    // IMPORTANT: Send JSON body + Content-Type application/json + X-USER header
    await api("/api/posts", {
      method: "POST",
      headers: { "X-USER": username },
      body: JSON.stringify({ content: content.trim() })
    });
  
    setMsg("postMsg", "Posted ✅");
    const contentEl = document.getElementById("postContent");
    if (contentEl) contentEl.value = "";
    await loadFeed();
  }
  
  function wireUi() {
    document.getElementById("btnSignup")?.addEventListener("click", () => {
      signup().catch(e => setMsg("signupMsg", e.message, false));
    });
  
    document.getElementById("btnPost")?.addEventListener("click", () => {
      createPost().catch(e => setMsg("postMsg", e.message, false));
    });
  
    document.getElementById("btnRefresh")?.addEventListener("click", () => {
      loadFeed().catch(e => setMsg("postMsg", e.message, false));
    });
  }
  
  document.getElementById("btnLogin")?.addEventListener("click", () => {
    (async () => {
      const username = (document.getElementById("liUser")?.value || "").trim();
      const password = document.getElementById("liPass")?.value || "";
  
      if (!username) return setMsg("loginMsg", "Username is required.", false);
      if (!password) return setMsg("loginMsg", "Password is required.", false);
  
      const resp = await api("/api/auth/login", {
        method: "POST",
        body: JSON.stringify({ username, password })
      });
  
      setMsg("loginMsg", `Login successful ✅ Token: ${resp.token}`, true);
      const postUserEl = document.getElementById("postUser");
      if (postUserEl) postUserEl.value = username;
    })().catch(e => setMsg("loginMsg", e.message, false));
  })
  // Boot
  wireUi();
  loadFeed().catch(e => setMsg("postMsg", e.message, false));
  