<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <h1 class="text-center">線上圖書借閱系統</h1>
          </div>
          <div class="card-body">
            <form @submit.prevent="login">
              <div class="mb-3 row">
                <label for="phoneNumber" class="col-sm-3 col-form-label">電話號碼：</label>
                <div class="col-sm-9 text-end">
                  <input v-model="phoneNumber" type="text" class="form-control" required />
                </div>
              </div>
              <div class="mb-12 row ">
                <label for="password" class="col-sm-3 col-form-label">密碼：</label>
                <div class="col-sm-9 text-end">
                  <input v-model="password" type="password" class="form-control" required />
                </div>
              </div>
              <div class="me-3 text-end" > 
                <button type="submit" class="btn btn-primary me-3" style="margin-top: 5px;margin-right: 10px;">登入</button>
                <button type="button" @click="register" class="btn btn-primary">註冊</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>




<script>
export default {
  data() {
    return {
      phoneNumber: '',
      password: '',
    };
  },
  methods: {
    login() {
      // Handle login logic
      console.log('Logging in...');
    },
    register() {
      // Handle registration logic
      console.log('Registering...');
    },
  },
};
</script>
