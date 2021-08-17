<template>
  <div class="text-center">
    <v-pagination
      v-model="pageNumber"
      :length="pageLength"
      :total-visible="7"
      @input="getNumber"
    ></v-pagination>
  </div>
</template>
<script>
export default {
  props: {
    pageOption: {
      type: Object,
      require: false,
      default: () => {},
    },
  },
  data() {
    return {
      pageNumber: 1,
    };
  },
  computed: {
    pageLength() {
      if (this.pageOption == null || this.pageOption == undefined) {
        return 1;
      }
      if (
        this.pageOption.total == null ||
        this.pageOption.total == undefined ||
        this.pageOption.total == 0
      ) {
        return 1;
      }
      if (
        this.pageOption.number == null ||
        this.pageOption.number == undefined ||
        this.pageOption.number == 0
      ) {
        return 1;
      }
      if (total < number) {
        return 1;
      }

      let total = this.pageOption.total;
      let number = this.pageOption.number;
      return Math.ceil(total / number);
    },
  },
  methods: {
    getNumber(number) {
      this.pageNumber = number;
      console.log("ページ切り替え");
      this.$emit("paging");
    },
    newPage() {},
    getPage() {
      console.log("ページ数取得");
      return this.pageNumber;
    },
  },
};
</script>