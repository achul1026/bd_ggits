function getSecureRandomNum() {
    return (window.crypto || window.msCrypto).getRandomValues(new Uint32Array(1))[0]/4294967296;
}