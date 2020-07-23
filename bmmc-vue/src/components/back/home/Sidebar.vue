<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
        >
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-menu-item
                                :index="subItem.index"
                                :key="subItem.index"
                            >{{ subItem.title }}
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
import bus from './bus';
export default {
    data() {
        return {
            collapse: false,
            items: [
                {
                    icon: 'el-icon-zf-home',
                    index: 'homeindex',
                    title: '系统首页'
                },
                {
                    icon: 'el-icon-zf-user',
                    index: 'usermanage',
                    title: '用户管理'
                },
                {
                    icon: 'el-icon-zf-book',
                    index: '1',
                    title: '阅读管理',
                    subs: [
                        {
                            index: 'bookmanage',
                            title: '书籍管理'
                        },
                        {
                            index: 'chaptermanage',
                            title: '章节管理'
                        },
                        {
                            index: 'bookcomment',
                            title: '书评管理'
                        },
                    ]
                },
                {
                    icon: 'el-icon-zf-movie',
                    index: '2',
                    title: '电影管理',
                    subs: [
                        {
                            index: 'moviemanage',
                            title: '电影管理'
                        },
                        {
                            index: 'planmanage',
                            title: '排片管理'
                        },
                        {
                            index: 'moviecomment',
                            title: '影评管理'
                        },
                    ]
                },
                {
                    icon: 'el-icon-zf-music',
                    index: '3',
                    title: '音乐管理',
                    subs: [
                        {
                            index: 'musicmanage',
                            title: '音乐管理'
                        },
                        {
                            index: 'musiccomment',
                            title: '乐评管理'
                        },
                    ]
                },
                {
                    icon: 'el-icon-zf-order',
                    index: 'ordermanage',
                    title: '订单管理'
                },
            ]
        };
    },
    computed: {
        onRoutes() {
            return this.$route.path.replace('/', '');
        }
    },
    created() {
        // 通过 Event Bus 进行组件间通信，来折叠侧边栏
        bus.$on('collapse', msg => {
            this.collapse = msg;
            bus.$emit('collapse-content', msg);
        });
    }
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>
