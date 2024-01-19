<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <h1 class="text-center">線上圖書借閱系統</h1>
          </div>
          <div class="card-body">
            <form @submit.prevent="login">
              <div class="mb-3 row">
                <label for="phoneNumber" class="col-sm-3 col-form-label"
                  >電話號碼：</label
                >
                <div class="col-sm-9 text-end">
                  <input
                    v-model="phoneNumber"
                    type="text"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="mb-12 row">
                <label for="password" class="col-sm-3 col-form-label"
                  >密碼：</label
                >
                <div class="col-sm-9 text-end">
                  <input
                    v-model="password"
                    type="password"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="me-3 text-end">
                <button
                  type="submit"
                  class="btn btn-primary me-3"
                  style="margin-top: 5px; margin-right: 10px"
                >
                  登入
                </button>
                <button
                  type="button"
                  @click="openRegisterModal"
                  class="btn btn-primary"
                >
                  註冊
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- 註冊彈出視窗 -->
    <dialog v-if="isRegisterModalVisible" id="registerModal" class="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">註冊</h5>
            <button
              type="button"
              class="btn-close"
              @click="closeRegisterModal"
            ></button>
          </div>
          <div class="modal-body">
            <!-- 註冊表單 -->
            <label for="registername">用戶名：</label>
            <input
              v-model="registername"
              type="text"
              class="form-control"
              required
            />

            <label for="registerPhoneNumber">電話號碼：</label>
            <input
              v-model="registerPhoneNumber"
              type="text"
              class="form-control"
              required
            />

            <label for="registerPassword">密碼：</label>
            <input
              v-model="registerPassword"
              type="password"
              class="form-control"
              required
            />
            <button
              type="button"
              class="btn btn-primary"
              style="margin-top: 5px"
              @click="submitRegister"
            >
              註冊
            </button>
          </div>
        </div>
      </div>
    </dialog>
  </div>
</template>

<script>
import authService from "@/API/authService";
import router from "@/router";
export default {
  data() {
    return {
      isRegisterModalVisible: false,
      phoneNumber: "",
      password: "",
      registername: "",
      registerPhoneNumber: "",
      registerPassword: "",
    };
  },
  methods: {
    async login() {
      const { success, token, userId, message } = await authService.login(
        this.phoneNumber,
        this.password
      );

      if (success) {
        router.push("/");
        console.log("Login successful:", token, userId);
      } else {
        // 登入失败，处理逻辑
        console.error("Login failed:", message);
      }
    },
    openRegisterModal() {
      console.log(this.isRegisterModalVisible);
      this.isRegisterModalVisible = true;
    },
    closeRegisterModal() {
      this.isRegisterModalVisible = false;
    },
    async submitRegister() {
      const doRegister = await authService.register(
        this.registerPhoneNumber,
        this.registerPassword,
        this.registername
      );
      if (doRegister) {
        this.phoneNumber = this.registerPhoneNumber;
        this.password = this.registerPassword;
        this.registername = null;
        this.registerPhoneNumber = null;
        this.registerPassword = null;
        console.log("Register successful");
      }
      this.closeRegisterModal(); // 你可以根據實際需求在這裡添加相應的註冊邏輯
    },
  },
};
</script>

<style>
.dialog {
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-dialog {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
  border-bottom: 1px solid #ccc;
}

.modal-title {
  margin: 0;
}
</style>
