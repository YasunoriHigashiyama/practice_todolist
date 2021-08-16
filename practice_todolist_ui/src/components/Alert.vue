<template >
  <div v-if="getAction">
    <v-alert dense outlined type="error" class="multi-line">
      <v-row>
        <v-col cols="12" class="pb-1">
          <div v-html="getMessage"></div>

          <v-row>
            <div v-html="'\n' + getDetail"></div>

            <v-spacer />
            <v-btn
              color="error"
              text
              @click="
                store_setAlert({ action: false, message: null, detail: null })
              "
              >Close</v-btn
            >
          </v-row>
        </v-col>
      </v-row>
      <v-divider
        class="my-4 error"
        style="opacity: 0.92"
        v-if="this.getDetail != null"
      />
      <p>Please reload the page.</p>
    </v-alert>
  </div>
</template>
<script>
export default {
  data() {
    return { newLine: "\n" };
  },
  computed: {
    getAction() {
      return this.$store.getters["alert/action"];
    },
    getMessage() {
      return this.$store.getters["alert/message"];
    },
    getDetail() {
      return this.$store.getters["alert/detail"];
    },
  },
  mounted() {},
  methods: {
    store_setAlert({ action: action, message: message, detail: detail }) {
      this.$store.dispatch("alert/setAlert", {
        action: action,
        message: message,
        detail: detail,
      });
    },
  },
};
</script>

<style scoped>
.multi-line {
  white-space: pre-line;
}
</style>