<template>
  <div class="text-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" icon>
          <v-icon> mdi-dots-horizontal</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          @click="event(item.title)"
          v-for="(item, index) in items"
          :key="index"
        >
          <v-list-item-icon
            ><v-icon>{{ item.icon }}</v-icon></v-list-item-icon
          ><v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
export default {
  data: () => ({
    selectedItem: 1,
    items: [
      { title: "Edit", color: "primary", icon: "mdi-square-edit-outline" },
      { title: "Delete", color: "error", icon: "mdi-trash-can-outline" },
    ],
  }),
  methods: {
    event(title) {
      if (title == "Edit") {
        this.$emit("edit");
      }
      if (title == "Delete") {
        this.$emit("delete");
      }
    },
  },
};
</script>