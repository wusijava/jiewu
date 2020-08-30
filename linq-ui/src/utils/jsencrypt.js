import JSEncrypt from 'jsencrypt/bin/jsencrypt'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgmcdI6JOc7sAbqmByRjGAeYU3\n' +
  't2VcrAsVVCjvY1b7ABBQTrj+nuMuF7v7b/e9EddOmmILsZHQacwvZrnC+eeolWn3\n' +
  'NEmP1lEMtrNhrUoFdrw1dgysQTrFkqeDRjZxJgpTn7fl6ncQ9A1gEl2y+nxozHy8\n' +
  'taBvX/rNXHq6fPQmywIDAQAB'

const privateKey = 'MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKCZx0jok5zuwBuq\n' +
  'YHJGMYB5hTe3ZVysCxVUKO9jVvsAEFBOuP6e4y4Xu/tv970R106aYguxkdBpzC9m\n' +
  'ucL556iVafc0SY/WUQy2s2GtSgV2vDV2DKxBOsWSp4NGNnEmClOft+XqdxD0DWAS\n' +
  'XbL6fGjMfLy1oG9f+s1cerp89CbLAgMBAAECgYAIR8V5odwyhDDNfFxap/nXaOyQ\n' +
  'cdk9DPXJVLyDkzih2l/89AIfxqtp7flYPm6khe329IjHl5EwELRhYUeEoQPrnVe2\n' +
  'dIWqwmCrX+aXX5Aat+3STXAgaDEfheYTLzW0VP6tBHla0qvuDXYJVxjSpely8znf\n' +
  '8U4UYIMkgaqQUAG5CQJBAMwxqU2ZA6OaNdc5S5DtIgHrd61P7YKkpUuzLD2CfW7G\n' +
  'xR2PvWz/LA3kF8WBSo3HbHpZLwdk7lPPCj3s2wXYc9UCQQDJWL7rmtu+sKVIB/fL\n' +
  'VkagVnGfpEkjO+pB1UVvhT3VfgtG9WAFjkPMn2kYWHMgjwoO84HsgdXbMHVAHIjT\n' +
  'daAfAkAcQZ8JEuyS/isHLnGLi5USKPB1tkLKulf5YsDzfhQ3eSAb2CFBbQo+ndrA\n' +
  'RQT5G6iiQaclYc0tIdBsA1PHuk1FAkB63NuLu3Bx+SqyPKJ5gskQGA+3obE7fdkh\n' +
  'mPmI5p5nw0UwZJTWbwxyNagUwgxmhsJVg0P5xPRxjy1NFNxtZK5zAkBs84y6cTdj\n' +
  'IxkvTPbU/WfO5N+0bGYh0WhD1g0Wl9yWh7NEEUtkXdfp7acpILrVmeK/wq7p+bt3\n' +
  '0kTt/Oi+sbm2'

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对需要加密的数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey)
  return encryptor.decrypt(txt)
}

